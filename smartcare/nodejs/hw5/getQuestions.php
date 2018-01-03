<?php
        
		ini_set('display_errors', 1);
		
		$con = new mysqli("localhost", "root","root");
		
		if ($con->connect_error) {
			die("Connection failed: " . $con->connect_error);
		} 
		
		
        mysqli_select_db($con,"HW04");
        
		
		$jsonObj = array();
		
		$name1 = $_POST['name1'];
        
        if($name1!="null")
        {
			
			try
			{
				
			
			if($name1!=null)
			{
				
				
				$qidsql = "SELECT * FROM questionsUsers WHERE uemail = '".$name1."';";
				
				$qidResult = $con->query($qidsql);
				$qidcount= $qidResult->num_rows;
				
				 if($qidcount>0)
				 {
						
					for($i=0;$i<$qidcount;$i++)
					{
						$rowqid = mysqli_fetch_array($qidResult,MYSQLI_ASSOC);
						$tempQid = $rowqid["qid"];
						
						$timepostedsql = "SELECT * FROM questionsTimesPosted WHERE qid = ".$tempQid.";";
						
						$timePostedResult = $con->query($timepostedsql);
						$timepostedcount= $timePostedResult->num_rows;
						
						if($timepostedcount>0)
						{
							
							for($ii=0;$ii<$timepostedcount;$ii++)
							{
								$rowtimeposted = mysqli_fetch_array($timePostedResult,MYSQLI_ASSOC);
								
								$tempTimePostedResult = $rowtimeposted["timeId"];
								$tempTimePostedResultTimeStamp = $rowtimeposted["dtime"];
								
								
								$timeresponsesql = "SELECT qr.qid,qr.timeId,qr.optionid,qo.optionname,q.question FROM questionsUsers as qu JOIN questionsTimesPosted as tp on qu.qid = tp.qid JOIN questionsResponse as qr on  qr.timeId = tp.timeId AND qr.uemail = qu.uemail JOIN questionsOptions as qo on qo.optionid = qr.optionid and qo.qid = qr.qid JOIN questions as q on q.qid = qr.qid WHERE qu.uemail = '".$name1."' and qr.timeId = ".$tempTimePostedResult." and qr.qid = ".$tempQid;
								
								
								$timeresponseresult = $con->query($timeresponsesql);
								$timeresponsecount= $timeresponseresult->num_rows;
								
								if($timeresponsecount>0)
								{
									for($iii=0;$iii<$timeresponsecount;$iii++)
									{
										$rowtimeresponse = mysqli_fetch_array($timeresponseresult,MYSQLI_ASSOC);
										
										$timeIdTemp = $rowtimeresponse["timeId"];
										$optionIdTemp = $rowtimeresponse["optionid"];
										$optionNameTemp = $rowtimeresponse["optionname"];
										$questionActualTemp = $rowtimeresponse["question"];
										
										$row_array['qid'] = $tempQid;
										$row_array['timeId'] = $timeIdTemp;
										$row_array['optionId'] = $optionIdTemp;
										$row_array['optionName'] = $optionNameTemp;
										$row_array['response'] = "yes";
										$row_array['question'] = $questionActualTemp;
										$row_array['dtime'] = $tempTimePostedResultTimeStamp;
										
										array_push($jsonObj,$row_array);
										
									}
								}
								else
								{
									$qidResponseResultsql = "SELECT q.question,qo.qid,qo.optionid,qo.optionname FROM questionsOptions as qo JOIN questions as q on q.qid=qo.qid WHERE q.qid = ".$tempQid;
									
									$qidResponseResult = $con->query($qidResponseResultsql);
									$qidResponseResultcount= $qidResponseResult->num_rows;
									
									$questionActualTemp2 = "temp";

									
									if($qidResponseResultcount>0)
									{
										$qidRespObj = array();
										
										for($iiii=0;$iiii<$qidResponseResultcount;$iiii++)
										{
											$rowqidResponseResult = mysqli_fetch_array($qidResponseResult,MYSQLI_ASSOC);
											
											$qidResponseTempQid = $rowqidResponseResult["qid"];
											$qidResponseTempOptionId = $rowqidResponseResult["optionid"];
											$qidResponseTempOptionName = $rowqidResponseResult["optionname"];
											$questionActualTemp2 = $rowqidResponseResult["question"];
											
											
											$row_arrayOp['optionid'] = $qidResponseTempOptionId;
											$row_arrayOp['optionname'] = $qidResponseTempOptionName;

											array_push($qidRespObj,$row_arrayOp);
										
										}
										
										$row_arrayNo['qid'] = $tempQid;
										$row_arrayNo['timeId'] = $tempTimePostedResult;
										$row_arrayNo['options'] = $qidRespObj;
										$row_arrayNo['response'] = "no";
										$row_arrayNo['question'] = $questionActualTemp2;
										$row_arrayNo['dtime'] = $tempTimePostedResultTimeStamp;
										
										array_push($jsonObj,$row_arrayNo);
												
									}
									
									
								}
								
								
							}
							
						}
						

					}
					
					$row_arrayFinal['success'] = true;
					$row_arrayFinal['questions'] = $jsonObj;
					
					echo json_encode($row_arrayFinal);
				 
				 }
				
			}
			
			
			
			
			
		
		}
			catch(Exception $e)
			{
				echo $e->getMessage();
			}
            
            
            
            
            
        }
          
        
        ?>