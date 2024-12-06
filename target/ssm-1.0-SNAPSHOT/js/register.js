function registerApi(data) {
    return $axios({
        'url': 'ssm_war_exploded/users/register',
        'method': 'post',
        data
    })
}

function registerApi2(data) {
    return $axios({
        'url': 'ssm_war_exploded/business/register',
        'method': 'post',
        data
    })
}