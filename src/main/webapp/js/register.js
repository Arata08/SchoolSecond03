function registerApi(data) {
    return axiosservice({
        'url': '/users/register',
        'method': 'post',
        data
    })
}

function registerApi2(data) {
    return axiosservice({
        'url': '/business/register',
        'method': 'post',
        data
    })
}