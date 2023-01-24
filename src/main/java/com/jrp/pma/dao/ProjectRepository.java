package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="select stage as label, count(stage) as value "
			+ "from project "
			+ "group by stage "
			+ "order by 2 desc")
	public List<ChartData> getProjectStatuses();

	public Project findByProjectId(long theId);
	
	@Query(nativeQuery=true, value="select name as projectName, start_date as startDate, end_date as endDate "
			+ "from project where start_date is not null")
	public List<TimeChartData> getTimeData();
}
 