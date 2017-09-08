<?php

/**
 * Created by PhpStorm.
 * User: putuguna
 * Date: 1/24/2017
 * Time: 10:54 AM
 */
require_once("../db/Connection.php");
class InsertFood{
    function startInsertFood(){
        $connection = new Connection();
        $conn = $connection->getConnection();


        //array for json response
        $response = array();

        $foodName   = $_POST['foodname'];
        $foodQty    = $_POST['foodqty'];

        try{
            if(isset($foodName) && isset($foodQty)){
                $sqlInsert = "INSERT INTO food (foodname, foodqty) VALUES ('$foodName', '$foodQty')";
                $conn->exec($sqlInsert);
            }
        }catch (PDOException $e){
            echo "Error while inserting ".$e->getMessage();
        }

        //cek is the row was inserted or not
        if($sqlInsert){
            //success inserted
            $response["success"] = 1;
            $response["message"] = "Food successful inserted!";

            echo json_encode($response);

        }else{
            //failed inserted
            $response["success"] = 0;
            $response["message"] = "Failed while insert data";

            echo json_encode($response);
        }
    }
}

$insert = new InsertFood();
$insert->startInsertFood();