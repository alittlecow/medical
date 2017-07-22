package io.renren.modules.api.service.impl;

import io.renren.modules.api.dao.CardDao;
import io.renren.modules.api.entity.CardEntity;
import io.renren.modules.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service("cardService")
public class CardServiceImpl implements CardService {
	@Autowired
	private CardDao cardDao;
	
	@Override
	public CardEntity queryObject(String id){
		return cardDao.queryObject(id);
	}
	
	@Override
	public List<CardEntity> queryList(Map<String, Object> map){
		return cardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cardDao.queryTotal(map);
	}
	
	@Override
	public void save(CardEntity card){
		card.setId(UUID.randomUUID().toString().replace("-",""));
		cardDao.save(card);
	}
	
	@Override
	public void update(CardEntity card){
		cardDao.update(card);
	}
	
	@Override
	public void delete(String id){
		cardDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		cardDao.deleteBatch(ids);
	}
	
}
