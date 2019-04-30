package com.brocade.dcm.domain.mapper;

import com.brocade.dcm.domain.model.Emp;
import com.brocade.dcm.domain.model.EmpExample;
import com.brocade.dcm.domain.model.EmpInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EmpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    long countByExample(EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int deleteByExample(EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int deleteByPrimaryKey(Integer empno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int insert(Emp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int insertSelective(Emp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    List<Emp> selectByExample(EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    Emp selectByPrimaryKey(Integer empno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int updateByExampleSelective(@Param("record") Emp record, @Param("example") EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int updateByExample(@Param("record") Emp record, @Param("example") EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int updateByPrimaryKeySelective(Emp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.emp
     *
     * @mbg.generated Tue May 23 21:55:33 PDT 2017
     */
    int updateByPrimaryKey(Emp record);
    
    //
    /*
     * Mapper Annotations Illustration..
     */
    /*@Results(id = "empInfoResult", value = {
    		  @Result(property = "empno", column = "empno", id = true),
    		  @Result(property = "ename", column = "ename"),
    		  @Result(property = "job", column = "job")
    		})*/
    /**
     * @return
     */
    @Select("select emp.*, dept.dname, dept.loc from dept, emp where emp.deptno = dept.deptno")
    List<EmpInfo> getAllEmpInfo();
    
    @Select("select * from emp")
    List<Emp> getAllEmps();
    
    /**
     * @param empno
     * @return
     */
    @Select("select * from emp where empno = #{empno}")
    Emp getEmpById(Integer empno);
    //
}