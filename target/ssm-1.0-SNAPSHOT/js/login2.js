function loginApi2(data) {
    return $axios({
        'url': 'ssm_war_exploded/business/login',
        'method': 'post',
        data
    })
}