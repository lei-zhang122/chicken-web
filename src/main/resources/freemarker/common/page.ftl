<#-- 
 分页组件
    需要传入的参数 pageUrl ,用来生成的html地址，在pageUrl中必须有{page}在生成html地址的时候会自动把页码给赋值上去。页码起始值为1
    @pageNum 最大显示页 默认9
    @beforPage 前显示页 默认3
    @afterPage  后显示页 默认3
    @countPage 总页数   必须传入
    @currentPage 当前页 必须传入
    @currentPageClass 选中页码传递的样式，实现为直接将值贴给元素 <a href="#" ${currentPageClass}>
    @pageUrl  请求地址模板 ，内容中必须含有{page}用来替换页码
 -->
<#assign pageNum = pageNum!9>
<#assign beforPage = beforPage!3>
<#assign afterPage = afterPage!3>
<#assign countPage =countPage!0>
<#assign currentPage = currentPage!0>
<#assign currentPageClass = currentPageClass!"active">

<#if currentPage != 0 >
    <ul class="pagination">
            <#if currentPage != 1 >
            <li class="page-item">
                <a class="page-link" href="javascript:void(0);" onclick="page('${(currentPage-1)?c}')">PREVIOUS</a>
            </li>
            </#if>

            <#if (countPage > pageNum)><#-- 总页数大于最大显示页 -->
                <#if (pageNum > currentPage)><#-- 当前页小于显示页 -->
                    <#list 1..pageNum as index>
                    <li class="page-item <#if currentPage == index>${currentPageClass}</#if>">
                    <a href="javascript:void(0);" onclick="page('${index?c}')" class='page-link'  >${index?c}</a>
                    </li>
                    </#list>
                <#else>
                <li class="page-item">
                    <a href="javascript:void(0);" onclick="page('1')" class='page-link'>1</a>
                </li>
                <li class="page-item">
                    <a href="javascript:void(0);" class='page-link'>...</a>
                </li>
                    <#list currentPage-beforPage..currentPage + afterPage as index>
                        <#if (index > 0) && (index < countPage)>
                        <li class="page-item <#if currentPage == index>${currentPageClass}</#if>">
                            <a href="javascript:void(0);" onclick="page('${index?c}')" class='page-link'>${index?c}</a>
                        </li>
                        </#if>
                    </#list>
                </#if>
                <#if (currentPage < countPage - afterPage)>
                    <li class="page-item">
                    <a href="javascript:void(0);" class='page-link'>...</a>
                </li></#if>
            <li class="page-item <#if currentPage == countPage>${currentPageClass}</#if>">
            <a href="javascript:void(0);" onclick="page('${countPage?c}')" class='page-link'>${countPage?c}</a>
            </li>
            <#else><#-- 总页数小于显示页 -->
                    <#list 1..countPage as index>
                        <li class="page-item <#if currentPage == index>${currentPageClass}</#if>">
                            <a href="javascript:void(0);" onclick="page('${index?c}')" class='page-link'>${index?c}</a>
                        </li>
                    </#list>
            </#if>
            <#if countPage != currentPage>
            <li class="page-item">
                <a class="page-link" href="javascript:void(0);" onclick="page('${(currentPage+1)?c}')"">NEXT</a>
            </li>
            </#if>

    </ul>
</#if>