package pro.sky.telegrambot.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.services.ScheduledServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;


public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {

    @Query(value = "SELECT date_and_time FROM notification_task WHERE date_and_time = :currentDateTime", nativeQuery = true)
    Collection<NotificationTask> findByDateAndTime(@Param("currentDateTime") LocalDateTime currentDateTime);
}
