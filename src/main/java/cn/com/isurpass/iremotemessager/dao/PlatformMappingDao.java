package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.PlatformMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PlatformMappingDao extends CrudRepository<PlatformMapping, Integer> {
    PlatformMapping findByFromplatform(Integer platform);

    Page<PlatformMapping> findAll(Pageable pageable);

    void deleteByPlatformmappingidIn(Integer[] ids);
}
