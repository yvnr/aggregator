package com.jobboard.aggregator.mapper;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jobboard.aggregator.model.JobApplication;

@Mapper
public interface JobApplicationMapper {

	
	@Select("select * from job_applications_table where univ_id = #{univId} and applied_time >= #{startTime} and applied_time <= #{endTime}")
	public ArrayList<JobApplication> fetchAllApplicationByUnivInDateRange(long univId, Date startTime, Date endTime);
	
	
	
	
	@Insert("insert into job_applications_table (user_id, univ_id, company, position, status, job_id, location, applied_time, assessment_time, interview_time, response_time) values (#{userId}, #{univId}, #{company}, #{position}, #{status}, #{jobId}, #{location}, #{appliedTime}, #{assessmentTime}, #{interviewTime}, #{responseTime})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public long addNewApplication(JobApplication jobApplication);
	
	
	@Select("select * from job_applications_table where id = #{id}")
	@Results({
		@Result(property = "userId", column = "user_id"), 
		@Result(property = "univId", column = "univ_id"), 
		@Result(property = "jobId", column = "job_id"),
		@Result(property = "appliedTime", column = "applied_time"),
		@Result(property = "assessmentTime", column = "assessment_time"),
		@Result(property = "interviewTime", column = "interview_time"),
		@Result(property = "responseTime", column = "response_time")
	})
	public JobApplication findApplicationById(long id);
	
	@Update("update job_applications_table set company = #{company}, position = #{position}, job_id = #{jobId}, status = #{status},  location = #{location}, applied_time = #{appliedTime}, assessment_time = #{assessmentTime}, interview_time = #{interviewTime}, response_time = #{responseTime} where id = #{id}")
	public long updateJobApplication(JobApplication jobApplication);
	
	@Delete("delete from job_applications_table where id = #{id}")
	public void deleteJobApplicationWithId(long id);
	
	@Select("select * from job_applications_table where id >= #{startId} and user_id = #{userId} and univ_id = #{univId} order by id limit #{numberOfRecords}")
	@Results({
		@Result(property = "userId", column = "user_id"), 
		@Result(property = "univId", column = "univ_id"), 
		@Result(property = "jobId", column = "job_id"),
		@Result(property = "appliedTime", column = "applied_time"),
		@Result(property = "assessmentTime", column = "assessment_time"),
		@Result(property = "interviewTime", column = "interview_time"),
		@Result(property = "responseTime", column = "response_time")
	})
	public ArrayList<JobApplication> fetchJobApplications(long startId, long numberOfRecords, long userId, long univId);
	
	@Select("select count(*) from job_applications_table where user_id = #{userId} and univ_id = #{univId}")
	public Long fetchJobApplicationsCount(long userId, long univId);
	
	@Select("select count(*) from job_applications_table where user_id = #{userId} and univ_id = #{univId} and company = #{company} and job_id = #{jobId} and status = #{status}")
	public Long findApplicationByUniqueIdentifiers(JobApplication jobApplication);
	

	@Select("select count(*) from job_applications_table where user_id = #{userId} and univ_id = #{univId} and company = #{company} and job_id = #{jobId} and status = #{status}")
	public int findApplicationByUniqueIdentifiersWithEntity(JobApplication jobApplication);
	
	@Select("select id from job_applications_table where user_id = #{userId} and univ_id = #{univId} order by id limit 1")
	public Long getBeginId(long userId, long univId);
	
	@Select("select id from job_applications_table where user_id = #{userId} and univ_id = #{univId} order by id DESC limit 1")
	public Long getEndId(long userId, long univId);

}