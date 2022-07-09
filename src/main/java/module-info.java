/**
 * DDD実装補助APIを定義します。
 */
module raystark.ddds {
    requires io.vavr;

    exports raystark.ddds.aggregate;
    exports raystark.ddds.event;
    exports raystark.ddds.event.classbase;
    exports raystark.ddds.util;
    exports raystark.ddds.util.typemap;
}