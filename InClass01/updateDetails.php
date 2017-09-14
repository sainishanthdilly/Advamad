<?php


   require __DIR__.'/vendor/autoload.php';
        
   use Firebase\JWT\JWT;
        
$servername = "localhost";
$username = "id2733394_root";
$password = "2BV11is111";
$dbname = "id2733394_aminclass01";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$email = $_REQUEST["email"];
$fname = $_REQUEST["fname"];
$lname = $_REQUEST["lname"];
$age = $_REQUEST["age"];
$weight = $_REQUEST["weight"];

        
 $algorithm = 'HS256';
 $secretKey = base64_decode('mykey');
        

$sql = "update users set fname =  '".$fname."' , lname =  '".$lname."'
, age = ".$age.", weight =  ".$weight."   where email = '".$email."' ";


$conn->query($sql); 
  


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


try{

$jwt = JWT::encode(
    $data,      //Data to be encoded in the JWT
    $secretKey, // The signing key
    $algorithm  // Algorithm used to sign the token, see https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40#section-3
);

$unencodedArray = array('jwt' => $jwt);
echo json_encode($unencodedArray);

}catch(Exception $e)
{
echo $e->getMessage();
}







?>