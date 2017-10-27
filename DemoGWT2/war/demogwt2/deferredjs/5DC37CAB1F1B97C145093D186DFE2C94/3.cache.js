function onLoad_1(){
  loaded_0 = true;
  instance_1 = ($clinit_85() , new AsyncLoader3);
  $fragmentHasLoaded(($clinit_11() , BROWSER_LOADER), 3);
  !!$stats && $stats($createStatsEvent('runCallbacks3', 'begin', null, null));
  instance_1.runCallbacks();
  !!$stats && $stats($createStatsEvent('runCallbacks3', 'end', null, null));
}

function runCallbacks_4(){
  while (callbacksHead_0) {
    callbacksHead_0 = callbacksHead_0.next;
    !callbacksHead_0 && (callbacksTail_0 = null);
    $wnd.alert('Hello! Code Split');
  }
}

function AsyncLoader3(){
}

_ = AsyncLoader3.prototype = new AsyncLoader3__Super;
_.runCallbacks = runCallbacks_4;
_.typeId$ = 0;
onLoad_1();
