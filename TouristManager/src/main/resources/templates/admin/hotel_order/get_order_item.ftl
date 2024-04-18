<table class="table table-bordered">
    <thead>
    <tr>
        <th>#</th>
        <th>Room photos</th>
        <th>Room name</th>
        <th>Size</th>
        <th>Number of single beds</th>
        <th>Landscape</th>
        <th>Price</th>
        <th>Living population</th>
        <th>Days</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <#list orderItemList as orderItem>
        <tr>
            <th scope="row">${orderItem_index+1}</th>
            <td>
                <#if orderItem.hotelType.images?? && orderItem.hotelType.images?length gt 0>
                    <#list orderItem.hotelType.images?split(";") as images>
                        <#if images_index==0>
                            <img  src="/photo/view?filename=${images}" />
                        </#if>

                    </#list>
                </#if>
            </td>
            <td>${orderItem.hotelType.name!""}</td>
            <td>${orderItem.hotelType.area!""}</td>
            <td>${orderItem.hotelType.number!"0"}</td>
            <td>${orderItem.hotelType.landscape!""}</td>
            <td>${orderItem.hotelType.price!"0"}</td>
            <td>${orderItem.hotelType.guestNumber!"0"}</td>
            <td>${orderItem.day!"0"}</td>
            <td>${orderItem.money!"0"}</td>
        </tr>
    </#list>
    </tbody>
</table>
