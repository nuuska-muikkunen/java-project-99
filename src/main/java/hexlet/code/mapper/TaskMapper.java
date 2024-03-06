package hexlet.code.mapper;

import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.task.TaskDTO;
import hexlet.code.dto.task.TaskUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.model.Label;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskStatusRepository;
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
    private TaskStatusRepository taskStatusRepository;

    @Mapping(source = "assigneeId", target = "assignee")
    @Mapping(source = "slug", target = "taskStatus", qualifiedByName = "slugToTaskStatus")
    @Mapping(source = "taskLabelIds", target = "labels", qualifiedByName = "idsToLabels")
    public abstract Task map(TaskCreateDTO model);

    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(source = "taskStatus.slug", target = "slug")
    @Mapping(source = "labels", target = "taskLabelIds", qualifiedByName = "labelsToIds")
    public abstract TaskDTO map(Task model);

    @Mapping(source = "assigneeId", target = "assignee")
    @Mapping(source = "slug", target = "taskStatus", qualifiedByName = "slugToTaskStatus")
    @Mapping(source = "taskLabelIds", target = "labels", qualifiedByName = "idsToLabels")
    public abstract void update(TaskUpdateDTO update, @MappingTarget Task destination);

    @Named("slugToTaskStatus")
    public TaskStatus slugToTaskStatus(String slug) {
        return taskStatusRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("TaskStatus with slug " + slug + " not found"));
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
