Module(模块) -> Resource(资源) -> Menu(菜单)
User - Role(Authority) - Menu - Resource - Authority


```sql
DROP TABLE IF EXISTS uni_group;
CREATE TABLE uni_group(
    `id` bigint unsigned not null auto_increment comment '主键',
    `code` varchar(128) comment '',
    `hierarchy_code` varchar(516) comment '代码层次结构',
    `name` varchar(256) comment '',
    `description` varchar(516) comment '',
    `level` comment '',
    `sort` int unsigned comment '',
    `extend` json comment '',
    `is_deleted` tinyint(1) unsigned default 0 comment '',
    `created_by` varchar(256) comment '',
    `updated_by` varchar(256) comment '',
    `created_at` timestamp comment '',
    `updated_at` timestamp comment ''
    PRIMARY KEY(`id`) USING BTREE
)comment='' engine=InnoDB default charset=utf8mb4 collate=utf8mb4_general_ci;
DROP TABLE IF EXISTS uni_label;
CREATE

```