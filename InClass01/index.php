<?php
        
        require __DIR__.'/vendor/autoload.php';
        
        use Firebase\JWT\JWT;
        
		$con = new mysqli("localhost", "id2733394_root","2BV11is111");
		
		if ($con->connect_error) {
			die("Connection failed: " . $con->connect_error);
		} 
		
		
		
        mysqli_select_db($con,"id2733394_aminclass01");
        
            
        $jwt = $_SERVER['HTTP_TOKEN'];
        
        $algorithm = 'HS256';
        $secretKey = base64_decode('mykey');
        
        if($jwt!="null")
        {
			
			try
			{
         
            $DecodedDataArray = JWT::decode($jwt, $secretKey,array($algorithm));
			echo  json_encode($DecodedDataArray);

			}
			catch(Exception $e)
			{
				echo $e->getMessage();
			}
            
            
            
            //$decoded_array = (array) $token;
            
            //echo $decoded_array;

            
            

        }
        else
        {
        
                
        $email = $_REQUEST["email"];
        $pwd = $_REQUEST["password"];
        
		
		
		
        $sql = "select * from users where password = '".$pwd."' and email = '".$email."'";
        
        
      
        $result = $con->query($sql);
        $count= $result->num_rows;
		
		
        if($count>=1){
        
		
    			 $sql = "select fname, lname, age, weight from users where email ='".$email."'";
				 $result = $con->query($sql);

				$row = mysqli_fetch_row($result);

				$fname = $row[0];
				$lname = $row[1];
				$age = $row[2];
				$weight = $row[3];



            
             $tokenId    = base64_encode(mcrypt_create_iv(32));
             $issuedAt   = time();
             $notBefore  = $issuedAt;  //Adding 10 seconds
             $expire     = $notBefore + 5000; // Adding 60 seconds
             $serverName = 'localhost';
            
             $data = array(
                        'iat'  => $issuedAt,         // Issued at: time when the token was generated
                        'jti'  => $tokenId,          // Json Token Id: an unique identifier for the token
                        'iss'  => $serverName,       // Issuer
                        'nbf'  => $notBefore,        // Not before
                        'exp'  => $expire,           // Expire
                        'data' => array(                  // Data related to the signer user
                            'email' => $email,
							'fname' => $fname,
							'lname' => $lname,
							'age' => $age,
							'weight' => $weight,
                        )
                    );
             
			 
			 
			 try
			 {
			 
             $jwt = JWT::encode(
                        $data,      //Data to be encoded in the JWT
                        $secretKey, // The signing key
                        $algorithm  // Algorithm used to sign the token, see https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40#section-3
                        );
             
             $unencodedArray = array('jwt' => $jwt);
             echo json_encode($unencodedArray);
			 
			 }
			 catch(Exception $e)
			 {}

            
        }
        else {
     
            echo 'invalid';
            
        }  
        }
        ?>