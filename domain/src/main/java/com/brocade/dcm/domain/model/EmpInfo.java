/**
 * 
 */
package com.brocade.dcm.domain.model;

import java.util.Date;

/**
 * @author ml410408
 *
 */
public class EmpInfo {

	private Integer empno;
	
	private String ename;
	
	private String job;
	
	private Integer mgr;
	
	private Date hiredate;
	
	private Integer sal;
	
	private Integer comm;
	
	private Integer deptno;
	
	private String dname;
	
	private String loc;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comm == null) ? 0 : comm.hashCode());
		result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
		result = prime * result + ((dname == null) ? 0 : dname.hashCode());
		result = prime * result + ((empno == null) ? 0 : empno.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((loc == null) ? 0 : loc.hashCode());
		result = prime * result + ((mgr == null) ? 0 : mgr.hashCode());
		result = prime * result + ((sal == null) ? 0 : sal.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EmpInfo))
			return false;
		EmpInfo other = (EmpInfo) obj;
		if (comm == null) {
			if (other.comm != null)
				return false;
		} else if (!comm.equals(other.comm))
			return false;
		if (deptno == null) {
			if (other.deptno != null)
				return false;
		} else if (!deptno.equals(other.deptno))
			return false;
		if (dname == null) {
			if (other.dname != null)
				return false;
		} else if (!dname.equals(other.dname))
			return false;
		if (empno == null) {
			if (other.empno != null)
				return false;
		} else if (!empno.equals(other.empno))
			return false;
		if (ename == null) {
			if (other.ename != null)
				return false;
		} else if (!ename.equals(other.ename))
			return false;
		if (hiredate == null) {
			if (other.hiredate != null)
				return false;
		} else if (!hiredate.equals(other.hiredate))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (loc == null) {
			if (other.loc != null)
				return false;
		} else if (!loc.equals(other.loc))
			return false;
		if (mgr == null) {
			if (other.mgr != null)
				return false;
		} else if (!mgr.equals(other.mgr))
			return false;
		if (sal == null) {
			if (other.sal != null)
				return false;
		} else if (!sal.equals(other.sal))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"EmpInfo [empno=%s, ename=%s, job=%s, mgr=%s, hiredate=%s, sal=%s, comm=%s, deptno=%s, dname=%s, loc=%s]",
				empno, ename, job, mgr, hiredate, sal, comm, deptno, dname, loc);
	}

	/**
	 * 
	 */
	public EmpInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the empno
	 */
	public final Integer getEmpno() {
		return empno;
	}

	/**
	 * @param empno the empno to set
	 */
	public final void setEmpno(Integer empno) {
		this.empno = empno;
	}

	/**
	 * @return the ename
	 */
	public final String getEname() {
		return ename;
	}

	/**
	 * @param ename the ename to set
	 */
	public final void setEname(String ename) {
		this.ename = ename;
	}

	/**
	 * @return the job
	 */
	public final String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public final void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the mgr
	 */
	public final Integer getMgr() {
		return mgr;
	}

	/**
	 * @param mgr the mgr to set
	 */
	public final void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	/**
	 * @return the hiredate
	 */
	public final Date getHiredate() {
		return hiredate;
	}

	/**
	 * @param hiredate the hiredate to set
	 */
	public final void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	/**
	 * @return the sal
	 */
	public final Integer getSal() {
		return sal;
	}

	/**
	 * @param sal the sal to set
	 */
	public final void setSal(Integer sal) {
		this.sal = sal;
	}

	/**
	 * @return the comm
	 */
	public final Integer getComm() {
		return comm;
	}

	/**
	 * @param comm the comm to set
	 */
	public final void setComm(Integer comm) {
		this.comm = comm;
	}

	/**
	 * @return the deptno
	 */
	public final Integer getDeptno() {
		return deptno;
	}

	/**
	 * @param deptno the deptno to set
	 */
	public final void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	/**
	 * @return the dname
	 */
	public final String getDname() {
		return dname;
	}

	/**
	 * @param dname the dname to set
	 */
	public final void setDname(String dname) {
		this.dname = dname;
	}

	/**
	 * @return the loc
	 */
	public final String getLoc() {
		return loc;
	}

	/**
	 * @param loc the loc to set
	 */
	public final void setLoc(String loc) {
		this.loc = loc;
	}

}
