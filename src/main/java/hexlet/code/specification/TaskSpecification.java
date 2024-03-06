package hexlet.code.specification;

import hexlet.code.dto.task.TaskParamsDTO;
import hexlet.code.model.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TaskSpecification {
    public Specification<Task> build(TaskParamsDTO params) {
        return withNameCont(params.getNameCont())
                .and(withAssigneeId(params.getAssigneeId()))
                .and(withSlug(params.getStatus()))
                .and(withLabelId(params.getLabelId()));
    }

    private Specification<Task> withNameCont(String namecheck) {
        return (root, query, cb) -> namecheck == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")), "%" + namecheck.toLowerCase() + "%");
    }

    private Specification<Task> withAssigneeId(Long assigneeId) {
            return (root, query, cb) -> assigneeId == null
                    ? cb.conjunction()
                    : cb.equal(root.get("assignee").get("id"), assigneeId);
    }

    private Specification<Task> withSlug(String status) {
            return (root, query, cb) -> status == null
                    ? cb.conjunction()
                    : cb.equal(root.get("taskStatus").get("slug"), status);
    }

    private Specification<Task> withLabelId(Long labelId) {
            return (root, query, cb) -> labelId == null
                    ? cb.conjunction()
                    : cb.equal(root.get("labels").get("id"), labelId);
    }

}