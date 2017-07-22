package jubo.medical.modules.api.dao;

import jubo.medical.modules.api.entity.CardEntity;
import jubo.medical.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 21:42:07
 */
@Mapper
public interface CardDao extends BaseDao<CardEntity> {
	
}
