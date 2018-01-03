<?php
        
        require __DIR__.'/vendor/autoload.php';
        
        use Firebase\JWT\JWT;


        
		$con = new mysqli("localhost", "id2733394_root","2BV11is111");
		
		if ($con->connect_error) {
			die("Connection failed: " . $con->connect_error);
		} 
		
		
		
        mysqli_select_db($con,"id2733394_aminclass01");
               
        $algorithm = 'HS256';
        $secretKey = base64_decode('mykey');
        
        
                
        $email = $_REQUEST["email"];
        $password = $_REQUEST["password"];
        $age = $_REQUEST["age"];
		$fname = $_REQUEST["fname"];
		$lname = $_REQUEST["lname"];
		$weight = $_REQUEST["weight"];
		
		
		
		$tokenId    = base64_encode(mcrypt_create_iv(32));
        $issuedAt   = time();
        $notBefore  = $issuedAt;  //Adding 10 seconds
        $expire     = $notBefore + 5000; // Adding 60 seconds
        $serverName = 'localhost';
		$data = array();
		
		
        $sql = "select * from users where email = '".$email."'";
        $result = $con->query($sql);
        $count= $result->num_rows;
		
		
        if($count>=1){
        
            
             $data = array(
                        'iat'  => $issuedAt,         // Issued at: time when the token was generated
                        'jti'  => $tokenId,          // Json Token Id: an unique identifier for the token
                        'iss'  => $serverName,       // Issuer
                        'nbf'  => $notBefore,        // Not before
                        'exp'  => $expire,           // Expire
                        'data' => array(                  // Data related to the signer user
                            'id' => 0 // user already exists
                        )
                    );
             
            
        }
        else {
     
				$sql = "insert into users values('".$fname."','".$lname."','".$email."',".$age.",".$weight.",'".$password."');";
				
				$result = $con->query($sql);
				
				if($result=== TRUE)
				{
        		
					 $data = array(
								'iat'  => $issuedAt,         // Issued at: time when the token was generated
								'jti'  => $tokenId,          // Json Token Id: an unique identifier for the token
								'iss'  => $serverName,       // Issuer
								'nbf'  => $notBefore,        // Not before
								'exp'  => $expire,           // Expire
								'data' => array(                  // Data related to the signer user
									'id' => 1 // successfully inserted
								)
							);
             
        
            
				}
		
				else
				{
			
						$data = array(
								'iat'  => $issuedAt,         // Issued at: time when the token was generated
								'jti'  => $tokenId,          // Json Token Id: an unique identifier for the token
								'iss'  => $serverName,       // Issuer
								'nbf'  => $notBefore,        // Not before
								'exp'  => $expire,           // Expire
								'data' => array(                  // Data related to the signer user
									'id' => 2 // unknown error
								)
							);
		
				}
		
		
		}
		
		     $jwt = JWT::encode(
                        $data,      //Data to be encoded in the JWT
                        $secretKey, // The signing key
                        $algorithm  // Algorithm used to sign the token, see https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40#section-3
                        );
             
			 $unencodedArray = array('jwt' => $jwt);
             $jwtToken = json_encode($unencodedArray);
			 
			 $data = json_decode($jwtToken);
			 $jwt = $data->jwt;
			 
			 
			 
			$DecodedDataArray = JWT::decode($jwt, $secretKey, array($algorithm));
				
			echo json_encode($DecodedDataArray);
		
        
    ?>