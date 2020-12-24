package me.kyunghwan.junitstudy.study;

import me.kyunghwan.junitstudy.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}