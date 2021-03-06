package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.dao.PartitionDao;
import cn.com.isurpass.iremotemessager.domain.Partition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PartitionService {
    @Resource
    private PartitionDao partitionDao;

    public Partition findById(Integer id) {
        if (IRemoteUtils.isBlank(id)) {
            return new Partition();
        }
        return partitionDao.findById(id).orElse(new Partition());
    }
}
