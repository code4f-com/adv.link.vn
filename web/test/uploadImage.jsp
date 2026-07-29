<%-- 
    Document   : uploadImage
    Created on : Mar 26, 2014, 2:51:11 PM
    Author     : TUANPLA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #uploadPreview img{
            }</style>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <meta charset=utf-8 />
        <title>Multiple image upload with preview by Roko C.B.</title>
    </head>
    <body>
        <input type="file" id="choose" multiple="multiple" />
        <br>
        <div id="uploadPreview"></div>
        <script>
// var url = window.URL || window.webkitURL; // alternate use
            function readImage(file) {
                var reader = new FileReader();
                var image = new Image();
                reader.readAsDataURL(file);
                reader.onload = function(_file) {
                    image.src = _file.target.result;              // url.createObjectURL(file);
                    image.onload = function() {
                        var w = this.width,
                                h = this.height,
                                t = file.type, // ext only: // file.type.split('/')[1],
                                n = file.name,
                                s = ~~(file.size / 1024) + 'KB';
                        $('#uploadPreview').append('<img src="' + this.src + '"> ' + w + 'x' + h + ' ' + s + ' ' + t + ' ' + n + '<br>');
                    };
                    image.onerror = function() {
                        alert('Invalid file type: ' + file.type);
                    };
                };
            }
            $("#choose").change(function(e) {
                if (this.disabled)
                    return alert('File upload not supported!');
                var F = this.files;
                if (F && F[0])
                    for (var i = 0; i < F.length; i++)
                        readImage(F[i]);
            });
        </script>
    </body>
</html>
