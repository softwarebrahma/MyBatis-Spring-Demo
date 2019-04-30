package com.brocade.dcm.server.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brocade.dcm.domain.criteria.PortsSearchCriteria;
import com.brocade.dcm.domain.mapper.DeptMapper;
import com.brocade.dcm.domain.mapper.EmpMapper;
import com.brocade.dcm.domain.mapper.PostsMapper;
import com.brocade.dcm.domain.model.Dept;
import com.brocade.dcm.domain.model.Emp;
import com.brocade.dcm.domain.model.EmpInfo;
import com.brocade.dcm.domain.model.Posts;
import com.brocade.dcm.domain.model.PostsExample;
import com.brocade.dcm.server.common.CustomApplicationNonRunTimeException;

@Service
public class ObjectCacheService {
	
	@Autowired
	private PostsMapper postsMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private EmpMapper empMapper;
	
	@SpectreLocalReadOnlyTransaction
	public List<Posts> getPosts(String id, String author, String searchQuery) {
		System.out.println("==== in ObjectCacheService.getPosts ==== for id : " + id);
		System.out.println("==== in ObjectCacheService.getPosts ==== for searchQuery : " + searchQuery);
		List<Posts> posts = null;
		if (!id.equals("-1")) {
			posts = Arrays.asList(postsMapper.selectByPrimaryKey(id));
		 } else if (StringUtils.isNotEmpty(searchQuery)) {
			 PortsSearchCriteria criteria = new PortsSearchCriteria();
			 criteria.setSearchQuery(searchQuery);
			 posts = postsMapper.searchByCustomCriteria(criteria);
		 } else if (StringUtils.isNotEmpty(author)) {
			 posts = getPostsByAuthor(author);
		 } else {
			 posts = getAllPosts();
		 }
		System.out.println("==== in ObjectCacheService.getPosts ==== returning : " + Arrays.deepToString(posts.toArray()));
		return posts;
	}
	
	@SpectreLocalReadOnlyTransaction
	public List<Posts> getAllPosts() {
		System.out.println("==== in ObjectCacheService.getAllPosts ==== ");
		List<Posts> posts = null;
		posts = postsMapper.selectByExample(null);
		System.out.println("==== in ObjectCacheService.getAllPosts ==== returning " + posts.size() + " posts : " + Arrays.deepToString(posts.toArray()));
		return posts;
	}
	
	@SpectreLocalReadOnlyTransaction
	public List<Posts> getPostsByAuthor(String author) {
		System.out.println("==== in ObjectCacheService.getPostsByAuthor ==== for author : " + author);
		List<Posts> posts = null;
		PostsExample postsExample = new PostsExample();
		postsExample.createCriteria().andAuthorLike(author);
		posts = postsMapper.selectByExample(postsExample);
		System.out.println("==== in ObjectCacheService.getPostsByAuthor ==== returning " + posts.size() + " posts : " + Arrays.deepToString(posts.toArray()));
		return posts;
	}
	
	@SpectreLocalReadWriteTransaction
	public Integer putPost(Posts posts) {
		System.out.println("==== in ObjectCacheService.putPost ==== for id : " + posts.getId());
		Integer id = -1;
		id = postsMapper.insert(posts);
		System.out.println("==== in ObjectCacheService.putPost ==== inserted succesfully true/false : " + (id > 0));
		return id;
	}
	
	@SpectreLocalReadWriteTransaction
	public Integer updatePost(Posts posts) {
		System.out.println("==== in ObjectCacheService.updatePost ==== for id : " + posts.getId());
		Integer id = -1;
		id = postsMapper.updateByPrimaryKey(posts);
		System.out.println("==== in ObjectCacheService.updatePost ==== updated succesfully true/false : " + (id > 0));
		return id;
	}
	
	@SpectreLocalReadWriteTransaction
	public Integer deletePost(String id) {
		System.out.println("==== in ObjectCacheService.deletePost ==== for id : " + id);
		Integer retId = -1;
		retId = postsMapper.deleteByPrimaryKey(id);
		System.out.println("==== in ObjectCacheService.deletePost ==== deleted succesfully true/false : " + (retId > 0));
		return retId;
	}
	
	@SpectreLocalReadWriteTransaction
	public Boolean insertDepartmentWithEmployees(Dept dept, List<Emp> empList) {
		Boolean ret = true;
		try {
			System.out.println("==== in ObjectCacheService.insertDepartmentWithEmployees ==== for deptno : " + dept.getDeptno());
			Integer id = -1;
			id = deptMapper.insert(dept);
			System.out.println("==== in ObjectCacheService.insertDepartmentWithEmployees : department ==== inserted succesfully true/false : " + (id > 0));
			for (Emp emp : empList) {
				emp.setDeptno(dept.getDeptno());
				id = empMapper.insert(emp);
				System.out.println("==== in ObjectCacheService.insertDepartmentWithEmployees : employee ==== inserted succesfully true/false : " + (id > 0));
			}
		} catch (Exception e) {
			System.out.println("==== EXCEPTION in ObjectCacheService.insertDepartmentWithEmployees ==== message is : " + e.getMessage());
			System.out.println("==== EXCEPTION in ObjectCacheService.insertDepartmentWithEmployees ==== class is : " + e.getClass().getName());
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	@SpectreLocalReadOnlyTransaction
	public List<Dept> getDepts(Integer deptNo) {
		System.out.println("==== in ObjectCacheService.getDepts ==== for deptNo : " + deptNo);
		List<Dept> depts = null;
		if (!deptNo.equals(-1)) {
			depts = Arrays.asList(deptMapper.selectByPrimaryKey(deptNo));
		 } else {
			 System.out.println("==== in ObjectCacheService.getDepts Getting all Depts ==== ");
			 depts = deptMapper.selectByExample(null);
			 //MUTHU
			 //depts = deptMapper.selectDepts();
			 System.out.println("TESTING 1 : " + empMapper.getEmpById(3));
			 System.out.println("TESTING 2 : " + Arrays.deepToString(empMapper.getAllEmpInfo().toArray()));
			 //MUTHU
		 }
		System.out.println("==== in ObjectCacheService.getDepts ==== returning : " + Arrays.deepToString(depts.toArray()));
		return depts;
	}
	
	@SpectreLocalReadOnlyTransaction
	public List<Emp> getEmps(Integer empNo) {
		System.out.println("==== in ObjectCacheService.getEmps ==== for empNo : " + empNo);
		List<Emp> emps = null;
		if (!empNo.equals(-1)) {
			//emps = Arrays.asList(empMapper.selectByPrimaryKey(empNo));
			emps = Arrays.asList(empMapper.getEmpById(empNo));
		 } else {
			 System.out.println("==== in ObjectCacheService.getEmps Getting all Emps ==== ");
			 //emps = empMapper.selectByExample(null);
			 emps = empMapper.getAllEmps();
		 }
		System.out.println("==== in ObjectCacheService.getEmps ==== returning : " + Arrays.deepToString(emps.toArray()));
		return emps;
	}
	
	@SpectreLocalReadOnlyTransaction
	public List<EmpInfo> getEmpInfos() {
		System.out.println("==== in ObjectCacheService.getEmpInfos Getting all EmpInfos ==== ");
		return empMapper.getAllEmpInfo();
	}
	
	@SpectreLocalCustomReadWriteTransaction
	public Integer putDeptOuter(Dept dept) throws CustomApplicationNonRunTimeException {
		Integer i = -1;
		try {
		i = putDept(dept);
		System.out.println("==== in ObjectCacheService.putDeptOuter ==== inserted succesfully true/false : " + (i > 0));
		if (true)
			throw new CustomApplicationNonRunTimeException();
		} catch(CustomApplicationNonRunTimeException ex) {
			System.out.println("==== in ObjectCacheService.putDeptOuter ==== EXCEPTION THROWN & CAUGHT IN OUTER METHOD : " + ex.getClass().getName());
			throw ex;
		}
		return i;
	}
	
	@SpectreLocalCustomReadWriteTransaction
	public Integer putDept(Dept dept) {
		System.out.println("==== in ObjectCacheService.putDept ==== for deptNo : " + dept.getDeptno());
		Integer id = -1;
		id = deptMapper.insert(dept);
		System.out.println("==== in ObjectCacheService.putDept ==== inserted succesfully true/false : " + (id > 0));
		return id;
	}
}
