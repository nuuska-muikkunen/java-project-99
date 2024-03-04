package hexlet.code.specification;

import hexlet.code.dto.task.TaskParamsDTO;
import hexlet.code.model.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TaskSpecification {
    public Specification<Task> build(TaskParamsDTO params) {
        return withTitleCont(params.getTitleCont())
                .and(withAssigneeId(params.getAssigneeId()))
                .and(withSlug(params.getSlug()))
                .and(withLabelId(params.getLabelId()));
    }

    private Specification<Task> withTitleCont(String title) {
        return (root, query, cb) -> title == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("title")), "%" + title + "%");
    }

    private Specification<Task> withAssigneeId(Long assigneeId) {
            return (root, query, cb) -> assigneeId == null
                    ? cb.conjunction()
                    : cb.equal(root.get("assigneeId"), assigneeId);
    }

    private Specification<Task> withSlug(String slug) {
            return (root, query, cb) -> slug == null
                    ? cb.conjunction()
                    : cb.equal(root.get("slug"), slug);
    }

    private Specification<Task> withLabelId(Long labelId) {
            return (root, query, cb) -> labelId == null
                    ? cb.conjunction()
                    : cb.equal(root.get("labelId"), labelId);
    }

}
