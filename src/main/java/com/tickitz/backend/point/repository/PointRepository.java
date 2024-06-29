package com.tickitz.backend.point.repository;

import com.tickitz.backend.point.dao.ResponsePointDao;
import com.tickitz.backend.point.dto.PointResponseDto;
import com.tickitz.backend.point.entity.Point;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
  @Query(value = "SELECT p.id as id, p.point as point, u.id as userId FROM Point p JOIN p.user u")
  List<ResponsePointDao> findAllWithUser();
  @Query(value = "SELECT COALESCE(SUM(p.point), 0) as totalCount FROM Point p WHERE p.user.id = :userId AND p.createdAt >= :expiredDay")
  Long getUserTotalPoints(@Param("userId") Long userId, @Param("expiredDay") Instant expiredDay);


}
