package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.util.JsonResult;
import cn.com.isurpass.iremotemessager.dao.PlatformMappingDao;
import cn.com.isurpass.iremotemessager.domain.PlatformMapping;
import cn.com.isurpass.iremotemessager.vo.PlatformMappingVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class PlatformMappingService {
    private Log log = LogFactory.getLog(PlatformMappingService.class);
    @Resource
    private PlatformMappingDao platformMappingDao;

    public Integer getPlatformMapping(Integer platform){
        PlatformMapping mapping = platformMappingDao.findByFromplatform(platform);
        return mapping == null ? null : mapping.getToplatform();
    }

    public Map<String, Object> listPlatformMapping(Pageable pageable) {
        Page<PlatformMapping> all = platformMappingDao.findAll(pageable);
        ArrayList<PlatformMappingVo> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        Iterator<PlatformMapping> iterator = all.iterator();
        while (iterator.hasNext()) {
            PlatformMapping mapping = iterator.next();
            PlatformMappingVo vo = new PlatformMappingVo();
            try {
                PropertyUtils.copyProperties(vo, mapping);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            list.add(vo);
        }
        map.put("total", platformMappingDao.count());
        map.put("rows", list);

        return map;
    }

    public JsonResult addMapping(Integer fromplatform, Integer toplatform) {
        if (fromplatform.equals(toplatform)) {
            return new JsonResult(-1, "添加失败");
        }
        PlatformMapping mapping = platformMappingDao.findByFromplatform(fromplatform);
        if (mapping != null) {
            return new JsonResult(-1, "添加失败");
        }
        platformMappingDao.save(new PlatformMapping(fromplatform, toplatform));
        return new JsonResult(1, "添加成功");
    }

    public JsonResult deleteMapping(String[] ids) {
        Integer[] inds = translate(ids);
        platformMappingDao.deleteByPlatformmappingidIn(inds);
        return new JsonResult(1, "删除成功");
    }

    private Integer[] translate(String[] ids) {
        Integer[] inds = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            try {
                inds[i] = Integer.valueOf(ids[i]);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return inds;
    }
}
