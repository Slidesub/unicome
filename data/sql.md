## 初始化数据库
```
use unicome_oauth

db.createCollection("user")  
db.createCollection("client")
db.createCollection("role")
db.createCollection("resource")

db.user.insertMany([{
    "userId": "001",
    "username": "admin001",
    "mobile": "15562305255",
    "password":"123456",
    "authorities": ["ROLE_ADMIN"]
    "expiredDate": ISODate("1991-12-31T16:00:00.000Z"),
    "createDate": ISODate("1991-12-31T16:00:00.000Z"),
    "updateDate": ISODate("1991-12-31T16:00:00.000Z")
}])

db.user.insertMany([
{
    "clientId": "adminClient",
    "clientSecret": "123456",
    "clientName": "admin client",
    "description": "测试管理员",
    "resourceIds": ["adminClientRes"],
    "authorizedGrantTypes": ["client_credentials", "authorization_code"],
    "registeredRedirectUri": ["http://localhost:17008/login"],
    "scope": ["admin_scope"],
    "authorities": ["ROLE_ADMIN"],
    "accessTokenValiditySeconds": 3600,
    "refreshTokenValiditySeconds": 3600
}
])


    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private Set<String> scope;
    private Collection<GrantedAuthority> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation;
    // 自定义字段
    private String clientName;
    private String description;
db.client.insertMany([
{
    "userName": "test",
    "realName": "测试管理员",
    "password": "$2a$10$7ZqbOVJdjDs6vdpbNNsQduqvGEKyuIdDwzNuRUfNvmNzUOiKpujl6",
    "department": "",
    "phone": "13023193997",
    "status": 0,
    "createTime": ISODate("1991-12-31T16:00:00.000Z"),
    "createUser": "test",
    "updateTime": ISODate("2018-07-03T00:27:20.402Z"),
    "updateUser": "test",
    "roles": ["ROLE_ADMIN"]
}
])

db.client.insertMany([
{
    "clientId": "OauthAdminServerClientId",
    "clientSecret": "{bcrypt}$2a$10$Jdxz8ZWyXLgvAYygXmPJXOLL01jk8wqsRKKhyTCy3ho7RUMOz504G",
    "clientName": "OauthAdminServer",
    "clientDescription": "Oauth Admin Server Description",
    "authorizedGrantTypes": ["client_credentials", "authorization_code"],
    "registeredRedirectUri": ["http://localhost:17007/oauth-admin-server/login"],
    "resourceIds": ["oauth-admin"],
    "grantedAuthorities": ["ROLE_CLIENT"],
    "scope": ["checkUser"],
    "createTime": ISODate("2018-07-03T01:32:21.211Z"),
    "createUser": "test"
}
])

db.client.insertMany([
{
    "clientId": "noah_admin",
    "clientSecret": "{bcrypt}$2a$10$Jdxz8ZWyXLgvAYygXmPJXOLL01jk8wqsRKKhyTCy3ho7RUMOz504G",
    "clientName": "NoahAdminServer",
    "clientDescription": "Noah Admin Server Description",
    "authorizedGrantTypes": ["client_credentials", "authorization_code"],
    "registeredRedirectUri": ["http://localhost:17008/login"],
    "resourceIds": ["oauth-admin"],
    "grantedAuthorities": ["ROLE_CLIENT"],
    "scope": ["checkUser"],
    "createTime": ISODate("2018-07-03T01:32:21.211Z"),
    "createUser": "test"
}
])

db.client.insertMany([
{
    "clientId": "ib_admin",
    "clientSecret": "{bcrypt}$2a$10$Jdxz8ZWyXLgvAYygXmPJXOLL01jk8wqsRKKhyTCy3ho7RUMOz504G",
    "clientName": "IBoardingAdminServer",
    "clientDescription": "IBoarding Admin Server Description",
    "authorizedGrantTypes": ["client_credentials", "authorization_code"],
    "registeredRedirectUri": ["http://localhost:17009/login"],
    "resourceIds": ["noah-admin"],
    "grantedAuthorities": ["ROLE_CLIENT"],
    "scope": ["checkUser"],
    "createTime": ISODate("2018-07-03T01:32:21.211Z"),
    "createUser": "test"
}
])

db.role.insertMany([
{
    "roleCode": "ROLE_CLIENT",
    "roleName": "客户端",
    "status": 0,
    "roleDescription": "客户端",
    "createTime": ISODate("2018-07-12T01:35:09.570Z"),
    "createUser": "test",
    "clientIds": ["OauthAdminServerClientId"]
}
,
{
    "roleCode": "ROLE_ADMIN",
    "roleName": "管理员",
    "status": 0,
    "roleDescription": "管理员",
    "createTime": ISODate("2018-07-15T09:48:53.711Z"),
    "createUser": "test",
    "userNames": ["test"]
}
,
{
    "roleCode": "ROLE_USER",
    "roleName": "普通用户",
    "status": 0,
    "roleDescription": "普通用户",
    "createTime": ISODate("2018-07-10T11:58:30.215Z"),
    "createUser": "test"
}
])

db.resource.insertMany([
{
    "resourceCode": "oauth-admin",
    "resourceName": "AUTH后台管理",
    "resourceDescription": "AUTH统一后台管理系统资源",
    "status": 0,
    "createTime": ISODate("2018-07-12T01:31:51.787Z"),
    "createUser": "test",
    "clientIds": ["OauthAdminServerClientId"]
}
])

---cms
use unicome_cms

db.createCollection("article")
db.createCollection("file_entry")
db.createCollection("tag")
```