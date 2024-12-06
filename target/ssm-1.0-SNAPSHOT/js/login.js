function loginApi(data) {
  return $axios({
    'url': 'ssm_war_exploded/users/login',
    'method': 'post',
    data
  })
}