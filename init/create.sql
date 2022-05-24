create schema gnb;

use gnb;

-- auto-generated definition
create table headlines
(
    id                  bigint auto_increment
        primary key,
    title               varchar(128)                         not null comment '제목',
    desktop_description text                                 not null comment 'PC 설명',
    mobile_description  text                                 null comment '모바일 설명',
    tablet_description  text                                 null comment '태블릿 설명',
    `order`             int        default 0                 not null comment '순서',
    service_link        text                                 null comment '서비스 알아보기 링크',
    title_key           varchar(32)                          null comment '제목 키',
    target_blank_flag   tinyint(1) default 0                 not null,
    created_at          datetime   default CURRENT_TIMESTAMP not null,
    updated_at          datetime   default CURRENT_TIMESTAMP not null,
    deleted_at          datetime                             null,
    constraint headlines_title_key_uindex
        unique (title_key)
)
    comment '대분류 제목';


-- auto-generated definition
create table titles
(
    id             bigint auto_increment
        primary key,
    headline_id    bigint                             not null comment '대분류 제목 연관 id',
    title          varchar(128)                       not null comment '제목',
    `order`        int      default 0                 not null comment '순서',
    change_pointer int      default 0                 not null comment '중분류 라인 넘어가는 기준',
    desktop_width  varchar(128)                       null comment 'PC에서 보여질 메뉴 길이',
    created_at     datetime default CURRENT_TIMESTAMP not null,
    updated_at     datetime default CURRENT_TIMESTAMP not null,
    deleted_at     datetime                           null,
    constraint titles_headline_id_fk
        foreign key (headline_id) references headlines (id)
            on update cascade on delete cascade
)
    comment '중분류 제목';


-- auto-generated definition
create table details
(
    id                bigint auto_increment
        primary key,
    title_id          bigint                                 not null comment '중분류 제목 연관 id',
    title             varchar(128)                           not null comment '제목',
    badge             varchar(256) default 'NONE'            not null comment '뱃지 상태',
    link              text                                   not null comment '링크 주소',
    icon              text                                   null comment '아이콘 주소',
    `order`           int          default 0                 not null comment '순서',
    target_blank_flag tinyint(1)                             null comment '1(true) : 새창
0(false) : 기존 창',
    sitemap_show_flag tinyint(1)                             null comment '1(true) : 사이트맵 노출
0(false) : 사이트맵 노출하지 않음',
    mobile_show_flag  tinyint(1)                             null comment '1(true) : 모바일 노출
0(false) : 모바일 노출하지 않음',
    created_at        datetime     default CURRENT_TIMESTAMP not null,
    updated_at        datetime     default CURRENT_TIMESTAMP not null,
    deleted_at        datetime                               null,
    constraint details_title_id_fk
        foreign key (title_id) references titles (id)
            on update cascade on delete cascade
)
    comment '소분류 제목';