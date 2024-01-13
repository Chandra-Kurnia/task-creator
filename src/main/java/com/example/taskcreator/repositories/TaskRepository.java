package com.example.taskcreator.repositories;

import com.example.taskcreator.dtos.TaskPayload;
import com.example.taskcreator.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT " +
            "tsk.task_id, " +
            "tsk.task_name, " +
            "tsk.due_date, " +
            "prt.priority_id, " +
            "prt.priority_name, " +
            "sts.status_id, " +
            "sts.status_name " +
            "from task tsk " +
            "JOIN priority prt ON tsk.priority_id = prt.priority_id " +
            "JOIN status sts ON tsk.status_id = sts.status_id " +
            "WHERE 1=1 " +
            "AND (COALESCE(:priorityIds) IS NULL OR prt.priority_id IN (:priorityIds)) " +
            "AND (COALESCE(:statusIds) IS NULL OR sts.status_id IN (:statusIds)) " +
            "AND (COALESCE(:taskName, '') = '' OR tsk.task_name LIKE %:taskName%)",
            nativeQuery = true)
    Page<Object[]> getTasks(
            @Param("priorityIds") List<Long> priorityIds,
            @Param("statusIds") List<Long> statusIds,
            @Param("taskName") String taskName,
            Pageable pageable);
}
