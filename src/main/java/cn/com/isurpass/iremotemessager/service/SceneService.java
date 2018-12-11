package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.SceneDao;
import cn.com.isurpass.iremotemessager.domain.Scene;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SceneService {
    @Resource
    private SceneDao sceneDao;

    public Scene findById(Integer scenedbid) {
        return sceneDao.findById(scenedbid).orElse(new Scene());
    }
}
