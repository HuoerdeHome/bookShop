// 封装一个快速发送Ajax请求的函数
function ajax(method, url, async, data, func, contentType) {
    let request = new XMLHttpRequest();
    request.open(method, url, async);
    request.setRequestHeader('Content-Type', contentType);
    request.send(data);
    request.onreadystatechange = () => {
        if (request.status === 200 &&
            request.readyState === 4) {
            func(JSON.parse(request.responseText));
        }
    }
}
