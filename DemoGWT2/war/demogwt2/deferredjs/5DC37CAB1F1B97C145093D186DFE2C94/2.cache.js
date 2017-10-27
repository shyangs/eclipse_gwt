function onLoad_0(){
  loaded = true;
  instance_0 = ($clinit_81() , new AsyncLoader2);
  $fragmentHasLoaded(($clinit_11() , BROWSER_LOADER), 2);
  !!$stats && $stats($createStatsEvent('runCallbacks2', 'begin', null, null));
  instance_0.runCallbacks();
  !!$stats && $stats($createStatsEvent('runCallbacks2', 'end', null, null));
}

function runCallbacks_2(){
  while (callbacksHead) {
    callbacksHead = callbacksHead.next;
    !callbacksHead && (callbacksTail = null);
    $wnd.alert('Hello! Code Split');
  }
}

function AsyncLoader2(){
}

_ = AsyncLoader2.prototype = new AsyncLoader2__Super;
_.runCallbacks = runCallbacks_2;
_.typeId$ = 0;
onLoad_0();
