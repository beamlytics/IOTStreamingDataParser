package com.beamlytics.examples.options;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;



public interface IOTDataPipelineOptions extends PipelineOptions {

  @Description("topic name")
  @Default.String("test-topic")
  public String getTopicName();

  void setTopicName(String topicName);


  @Description("test mode")
  @Default.Boolean(false)
  public Boolean getTestMode();

  public void setTestMode(Boolean testMode);

  @Description("Deadletter Table for pipeline.")
  @Default.String("IOTDataPipeline.deadletter_data")
  String getDeadLetterTable();

  void setDeadLetterTable(String deadletterBigQueryTable);

  @Description("Project used for data warehousing.")
  String getDataWarehouseOutputProject();
  void setDataWarehouseOutputProject(String dataWarehouseOutputProject);


  @Description("Table used for storing avergae temp.")
  String getAggregateTableName();
  void setAggregateTableName(String aggregateTableName);

  

}
