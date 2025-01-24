const base = {
    get() {
        return {
            url : "http://localhost:8080/zhiyuanzhaopinxitong/",
            name: "zhiyuanzhaopinxitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/zhiyuanzhaopinxitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "支教志愿者招聘"
        } 
    }
}
export default base
