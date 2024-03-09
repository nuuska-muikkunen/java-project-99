package hexlet.code.mapper;

import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.task.TaskDTO;
import hexlet.code.dto.task.TaskUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.model.Label;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Mapping(source = "title", target = "name")
    @Mapping(source = "content", target = "description")
    @Mapping(source = "assigneeId", target = "assignee", qualifiedByName = "idToUser")
    @Mapping(source = "status", target = "taskStatus", qualifiedByName = "statusToTaskStatus")
    @Mapping(source = "taskLabelIds", target = "labels", qualifiedByName = "idsToLabels")
    public abstract Task map(TaskCreateDTO model);


    @Mapping(source = "name", target = "title")
    @Mapping(source = "description", target = "content")
    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(source = "taskStatus.slug", target = "status")
    @Mapping(source = "labels", target = "taskLabelIds", qualifiedByName = "labelsToIds")
    public abstract TaskDTO map(Task model);


    @Mapping(source = "title", target = "name")
    @Mapping(source = "content", target = "description")
    @Mapping(source = "assigneeId", target = "assignee", qualifiedByName = "idToUser")
    @Mapping(source = "status", target = "taskStatus", qualifiedByName = "statusToTaskStatus")
    @Mapping(source = "taskLabelIds", target = "labels", qualifiedByName = "idsToLabels")
    public abstract void update(TaskUpdateDTO update, @MappingTarget Task destination);

    @Named("statusToTaskStatus")
    public TaskStatus statusToTaskStatus(String status) {
        return taskStatusRepository.findBySlug(status)
                .orElseThrow(() -> new ResourceNotFoundException("TaskStatus with slug " + status + " not found"));
    }

    @Named("idToUser")
    public User idToUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Named("idsToLabels")
    public Set<Label> idsToLabels(Set<Long> labelIds) {
        return labelIds == null ? new HashSet<>() : labelRepository.findByIdIn(labelIds);
    }

    @Named("labelsToIds")
    public Set<Long> labelsToIds(Set<Label> labels) {
        return labels == null ? new HashSet<>()
                : labels.stream()
                .map(Label::getId)
                .collect(Collectors.toSet());
    }
}
