var myScroll;
var pullDownEl;
var pullDownOffset;
var pullUpEl;
var pullUpOffset;
var flag = false;
function pullDownAction() {// 下拉事件
	setTimeout(function() {
		pull("down");
		myScroll.refresh(); // 刷新
	}, 1000); // 1秒
}
function pullUpAction() {// 上拉事件
	setTimeout(function() {
		pull("up");
		myScroll.refresh();
	}, 1000);
}
function loaded() {// 加载完成
	pullDownEl = document.getElementById('pullDown');
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById('pullUp');
	pullUpOffset = pullUpEl.offsetHeight;
	myScroll = new iScroll(
			'wrapper',
			{
				useTransition : true,
				topOffset : pullDownOffset,
				checkDOMChanges : true,// 是否自动检测内容变化
				onRefresh : function() {
					if (pullDownEl.className.match('loading')) {
						pullDownEl.className = '';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
					} else if (pullUpEl.className.match('loading')) {
						pullUpEl.className = '';
						pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉获取更多...';
					}
				},
				onScrollMove : function() {
					flag = true;
					if (this.y > 5 && !pullDownEl.className.match('flip')) {
						pullDownEl.className = 'flip';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手刷新...';
						this.minScrollY = 0;
					} else if (this.y < 5 && pullDownEl.className.match('flip')) {
						pullDownEl.className = '';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手刷新...';
						this.minScrollY = -pullDownOffset;
					} else if (this.y < (this.maxScrollY - 5)
							&& !pullUpEl.className.match('flip')) {
						pullUpEl.className = 'flip';
						pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手刷新...';
						this.maxScrollY = this.maxScrollY;
					} else if (this.y > (this.maxScrollY + 5)
							&& pullUpEl.className.match('flip')) {
						pullUpEl.className = '';
						pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉获取更多...';
						this.maxScrollY = pullUpOffset;
					}
				},
				onScrollEnd : function() {
					if (pullDownEl.className.match('flip') && flag) {
						pullDownEl.className = 'loading';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '玩命加载中...';
						pullDownAction(); // Execute custom function (ajax
											// call?)
						flag = false;
					} else if (pullUpEl.className.match('flip') && flag) {
						pullUpEl.className = 'loading';
						pullUpEl.querySelector('.pullUpLabel').innerHTML = '玩命加载中...';
						pullUpAction(); // Execute custom function (ajax call?)
						flag = false;
					}
				}
			});

	setTimeout(function() {
		document.getElementById('wrapper').style.left = '0';
	}, 800);
}

document.addEventListener('touchmove', function(e) {
	e.preventDefault();
}, false);

document.addEventListener('DOMContentLoaded', function() {
	setTimeout(loaded, 200);
}, false);
