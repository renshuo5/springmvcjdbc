package com.rs.distributor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rs.distributor.dao.DistributorDao;
import com.rs.distributor.vo.Distributor;

@Service
@Transactional(readOnly=false)
public class DistributorService {

	@Autowired
	private DistributorDao distributorDao;
	/**
	 * 
	 * @param distri
	 * @return 返回数据库插入的id
	 */
	public int saveDistributor(Distributor distri){
		return distributorDao.saveDistributor(distri);
	}
	
	/**
	 * 
	 * @param distri
	 * @return 1修改成功 0修改失败
	 */
//	public int updateDistributor(Distributor distri){
//		return distributorDao.updateDistributor(distri);
//	}
	
	public List<Distributor> findAll(){
		return distributorDao.findAll();
	}
	
	public Distributor findById(int id){
		return distributorDao.findById(id);
	}
	
//	public void detele(Distributor distri){
//		distributorDao.detele(distri);
//	}
}
