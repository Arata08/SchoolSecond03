function loginApi3(data) {
    return $axios({
        'url': 'ssm_war_exploded/admin/login',
        'method': 'post',
        data
    })
}