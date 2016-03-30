<html>
<body>
<h2>Hello Spring Security with OAuth2 !</h2>
<button id="gettoken">get token test</button>
<button id="acess">try to acess</button>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<%--scripts for get token--%>
<script>
    //function for access token
    var token;
    $('#acess').click(function () {
        sendGetJson('/api/users/', successResultAcess, errorToken, {access_token: token})
    });

    //success function for result
    function successResultAcess(result) {
        console.log(result);
        alert('get param successful look in console log')
    }
    //get token function
    $('#gettoken').click(function () {
        sendGetJson('/oauth/token', sucessget, errorToken, {
            grant_type: 'password', client_id: 'restapp',
            client_secret: 'restapp', username: 'beingjavaguys', password: 'spring@java'
        });
    });
    //token function
    function sucessget(html) {
        console.log(html);
        token = html.access_token;
        alert('token get successful look in console log')
    }
    //error token
    function errorToken() {
        alert('token get error')
    }
    //sender function
    function sendGetJson(url, methodSuccess, methodError, data) {
        $.ajax({
            url: url,
            type: 'GET',
            data: data,
            success: function (html) {
                methodSuccess(html);
            },
            error: function () {
                methodError();
            }
        });
    }
</script>
</body>
</html>
