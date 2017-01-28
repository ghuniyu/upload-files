<?php
if (isset($_FILES['text'])) {
    $is_ok = '';
    $errors = array();
    $file_name = $_FILES['text']['name'];
    $file_size = $_FILES['text']['size'];
    $file_tmp = $_FILES['text']['tmp_name'];
    $file_type = $_FILES['text']['type'];
    $file_ext = strtolower(end(explode('.', $_FILES['text']['name'])));

    $expensions = "txt";

    if ($file_ext != $expensions) {
        $errors[] = "Access Denied, please use only a Text file.";
    }

    if ($file_size > 100000) {
        $errors[] = 'Maximum Size is 100KB';
    }

    if (empty($errors) == true) {
        move_uploaded_file($file_tmp, "answer/" . $file_name);
        $is_ok = 'Success';
    } else {
//        print_r($errors[0]);
        $is_ok = $errors[0];
    }
}
?>
<!DOCTYPE html>
<!-- Simple Grading by NUB, TFD -->
<!-- Break me : WW91IG15IGZyaWVuZCwganVzdCBicmVhayB0aGlzIHNpbXBsZSBiYXNlNjQgQ29kZXMuLiANClNlY3JldCBDb2RlIDogZGFzcHJve3NvX3NpbXBsZV9mbGFnfQ -->
<html>
<head>
    <title>DASPRO LABS</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css" rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body style="background-color: #2196f3;">
<div class="container-fluid">
    <div class="row">
        <div class="col l4 offset-l4">
            <div class="row">
                <div class="center card-panel" style="margin-top: 50%;">
                    <h4>Submit Answer</h4>
                    <h6><?php
                        if (!empty($is_ok)) {
                            echo $is_ok;
                        } else {
                            echo 'DASPRO Laboratory';
                        }
                        ?>
                    </h6>
                    <form action="" method="POST" enctype="multipart/form-data">
                        <div class="file-field input-field">
                            <div class="file-field input-field" required>
                                <div class="btn blue waves-effect waves-light" required>
                                    <i class="small material-icons">note_add</i>
                                    <input name="text" type="file" required>
                                </div>
                                <div class="file-path-wrapper" required>
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                        </div>
                        <div>
                            <button class="btn blue waves-effect waves-light btn-large" type="submit">Submit
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
</html>