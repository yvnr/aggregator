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

/**
 * Mybatis mapper interface. The methods in this interface are responsible to interact with the Data base and execute corresponding SQL query to fetch/add data to Data Base
 *
 */
@Mapper
public interface JobApplicationMapper {

	/**
	 * Fetches all the job applications made from a university during specified date range.
	 * @param univId The unique ID assigned to the university by the authenticator
	 * @param startTime	The beginning of the date range
	 * @param endTime The end of the date range
	 * @return The list of fetched applications
	 */
	@Select("select * from job_applications_table where univ_id = #{univId} and applied_time >= #{startTime} and applied_time <= #{endTime}")
	@Results({
		@Result(property = "userId", column = "user_id"), 
		@Result(property = "univId", column = "univ_id"), 
		@Result(property = "jobId", column = "job_id"),
		@Result(property = "appliedTime", column = "applied_time"),
		@Result(property = "assessmentTime", column = "assessment_time"),
		@Result(property = "interviewTime", column = "interview_time"),
		@Result(property = "responseTime", column = "response_time")
	})
	public ArrayList<JobApplication> fetchAllApplicationByUnivInDateRange(String univId, Date startTime, Date endTime);
	
	/**
	 * Fetches job applications made from a university to a specific company during specified date range
	 * @param univId The unique ID assigned to the university by the authenticator
	 * @param company The name of the company
	 * @param startDate	The beginning of the date range
	 * @param endDate The end of the date range
	 * @return
	 */
	@Select("select * from job_applications_table where univ_id = #{univId} and company = #{company} and applied_time >= #{startDate} and applied_time <= #{endDate}")
	@Results({
		@Result(property = "userId", column = "user_id"), 
		@Result(property = "univId", column = "univ_id"), 
		@Result(property = "jobId", column = "job_id"),
		@Result(property = "appliedTime", column = "applied_time"),
		@Result(property = "assessmentTime", column = "assessment_time"),
		@Result(property = "interviewTime", column = "interview_time"),
		@Result(property = "responseTime", column = "response_time")
	})
	public ArrayList<JobApplication> fetchAllApplicationByUnivByCompanyInDateRange(String univId, String company, Date startDate, Date endDate);	
	
	/**
	 * Fetches job application by it's unique id
	 * @param id The unique id of the job application
	 * @return returns the job application with the give id
	 */
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
	
	/**
	 * Fetches a specified number of maximum records for a user beginning from a specified Job Id
	 * @param startId The beginning id of the job applications to be fetched
	 * @param numberOfRecords The maximum number of job applications to be fetched
	 * @param userId The unique id assigned to the user.
	 * @param univId The unique Id of the university/school
	 * @return returns a list of job applications satisfying all the conditions.
	 */
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
	
	/**
	 * Fetches the number of job applications made by a particular user
	 * @param userId The unique Id of the user.
	 * @param univId The unique Id of the university/school
	 * @return returns the count of applications made by the user.
	 */
	@Select("select count(*) from job_applications_table where user_id = #{userId} and univ_id = #{univId}")
	public Long fetchJobApplicationsCount(long userId, long univId);
	
	

	/**
	 * Fetches the id of the oldest job application made by a particular user
	 * @param userId The unique id of the user
	 * @param univId The unique Id of the university/school
	 * @return returns the id of the oldest job application made by the user.
	 */
	@Select("select id from job_applications_table where user_id = #{userId} and univ_id = #{univId} order by id limit 1")
	public Long getBeginId(long userId, long univId);
	
	/**
	 * Fetches the id of the latest job application made by a particular user
	 * @param userId The unique id of the user
	 * @param univId The unique Id of the university/school
	 * @return returns the id of the latest job application made by the user.
	 */
	@Select("select id from job_applications_table where user_id = #{userId} and univ_id = #{univId} order by id DESC limit 1")
	public Long getEndId(long userId, long univId);

	/**
	 * Inserts new Job application to the Database.
	 * @param jobApplication The job application details to be added to the Database
	 * @return returns number of records added to database.
	 */
	@Insert("insert into job_applications_table (user_id, univ_id, company, position, status, job_id, location, applied_time, assessment_time, interview_time, response_time) values (#{userId}, #{univId}, #{company}, #{position}, #{status}, #{jobId}, #{location}, #{appliedTime}, #{assessmentTime}, #{interviewTime}, #{responseTime})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public long addNewApplication(JobApplication jobApplication);
	
	/**
	 * Updates an existing job application with the new fields
	 * @param jobApplication The updated field values of the job application
	 * @return returns number of database rows updated.
	 */
	@Update("update job_applications_table set company = #{company}, position = #{position}, job_id = #{jobId}, status = #{status},  location = #{location}, applied_time = #{appliedTime}, assessment_time = #{assessmentTime}, interview_time = #{interviewTime}, response_time = #{responseTime} where id = #{id}")
	public long updateJobApplication(JobApplication jobApplication);
	
	/**
	 * Deletes a job application permanently from the database
	 * @param id The unique Id of the job application to be delete from the database.
	 */
	@Delete("delete from job_applications_table where id = #{id}")
	public void deleteJobApplicationWithId(long id);
	
}
