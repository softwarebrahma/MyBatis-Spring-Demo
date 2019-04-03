package com.brocade.dcm.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brocade.dcm.domain.model.Dept;
import com.brocade.dcm.domain.model.Emp;
import com.brocade.dcm.domain.model.Posts;
import com.brocade.dcm.server.service.ObjectCacheLookupService;
import com.brocade.dcm.server.service.ObjectCacheService;

@RestController
public class ObjectCacheServiceController {
	
	@Autowired
	private ObjectCacheService objectCacheService;
	
	@Autowired
	private ObjectCacheLookupService objectCacheLookupService;
	
	@GetMapping("/dcm/posts")
	 public ResponseEntity<List<Posts>> getPosts(@RequestParam(required=false, defaultValue="-1") String id, 
			 @RequestParam(required=false, defaultValue="") String author, @RequestParam(required=false, defaultValue="") String searchQuery) {
		return new ResponseEntity<>(objectCacheService.getPosts(id, author, searchQuery), HttpStatus.OK);
	}
	
	@PostMapping("/dcm/posts")
	public ResponseEntity<Integer> putPost(@RequestBody Posts posts) {
		return new ResponseEntity<>(objectCacheService.putPost(posts), HttpStatus.CREATED);
	}
	
	@PutMapping("/dcm/posts")
	public ResponseEntity<Integer> updatePost(@RequestBody Posts posts) {
		return new ResponseEntity<>(objectCacheService.updatePost(posts), HttpStatus.OK);
	}
	
	@DeleteMapping("/dcm/posts")
	 public ResponseEntity<Integer> deletePost(@RequestParam(required=false, defaultValue="1") String id) {
		return new ResponseEntity<>(objectCacheService.deletePost(id), HttpStatus.OK);
	}
	
	@PostMapping("/dcm/deptwithemps")
	public ResponseEntity<Boolean> putPost(@RequestBody DeptWithEmpsContext deptWithEmpsContext) {
		if (objectCacheService.insertDepartmentWithEmployees(deptWithEmpsContext.getDept(), deptWithEmpsContext.getEmpList())) {
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}		
	}
	
	private static final class DeptWithEmpsContext {
		private Dept dept;
		private List<Emp> empList;
		public Dept getDept() {
			return dept;
		}
		public void setDept(Dept dept) {
			this.dept = dept;
		}
		public List<Emp> getEmpList() {
			return empList;
		}
		public void setEmpList(List<Emp> empList) {
			this.empList = empList;
		}
	}
	
	@GetMapping("/dcm/depts")
	public ResponseEntity<List<Dept>> getDepts(@RequestParam(required=false, defaultValue="-1") Integer deptNo) {
		return new ResponseEntity<>(objectCacheService.getDepts(deptNo), HttpStatus.OK);
	}
	
	@GetMapping("/dcm/emps")
	public ResponseEntity<List<Emp>> getEmps(@RequestParam(required=false, defaultValue="-1") Integer empNo) {
		return new ResponseEntity<>(objectCacheService.getEmps(empNo), HttpStatus.OK);
	}
	
	@PostMapping("/dcm/depts")
	public ResponseEntity<Integer> putDept(@RequestBody Dept dept) {
		try {
			return new ResponseEntity<>(objectCacheService.putDeptOuter(dept), HttpStatus.OK);
		} catch(Exception e) {
			System.out.println("Caught : " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/dcm/test")
	public ResponseEntity<Void> testCacheLookup() {
		objectCacheLookupService.testCacheLookup();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
