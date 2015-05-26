<?php
function RegActionSes($report_type, $errors, $data, $nextUrl, $requestUrl, $message) {
    switch ($report_type) {
        case "failed_post_request": {
                $_SESSION['report_type'] = "failed_post_request";
                $_SESSION['errors'] = $errors;
                $_SESSION['post_data'] = $_POST;
                $_SESSION['nextUrl'] = $nextUrl;
                $_SESSION['requestUrl'] = $requestUrl;
                $_SESSION['report_message'] = (!empty($message) ? $message : "FAIL!");
                header(REPORT);
            }break;
        case "passed_post_request": {
                $_SESSION['report_type'] = "passed_post_request";
                $_SESSION['nextUrl'] = $nextUrl;
                $_SESSION['requestUrl'] = $requestUrl;
                $_SESSION['report_message'] = (!empty($message) ? $message : "SUCCES!");
                if (isset($_SESSION['errors'])) {
                    unset($_SESSION['errors']);
                    unset($_SESSION['post_data']);
                }
                header(REPORT);
            }break;
        case "failed_get_request": {
                $_SESSION['report_type'] = "failed_get_request";
                $_SESSION['nextUrl'] = $nextUrl;
                $_SESSION['requestUrl'] = $requestUrl;
                $_SESSION['report_message'] = (!empty($message) ? $message : "FAIL GET!");
                $_SESSION['errors'] = array("$requestUrl is niet gevonden op de server, heeft u de juiste url opgegeven?");
                header(REPORT);
            }break;
        case "passed_get_request": {
                $_SESSION['report_type'] = "passed_get_request";
                $_SESSION['nextUrl'] = $nextUrl;
                $_SESSION['requestUrl'] = $requestUrl;
                $_SESSION['report_message'] = (!empty($message) ? $message : "OK!");
                if (isset($_SESSION['errors'])) {
                    unset($_SESSION['errors']);
                    unset($_SESSION['post_data']);
                }
                header(REPORT);
            }break;
    }
}
