function runCallbacks(){
}

function AsyncLoader1__Super(){
}

_ = AsyncLoader1__Super.prototype = new Object_0;
_.runCallbacks = runCallbacks;
_.typeId$ = 0;
function $clinit_79(){
  $clinit_79 = nullMethod;
  instance = new AsyncLoader1__Super;
}

function onLoad(){
  instance = ($clinit_79() , new AsyncLoader1);
  $fragmentHasLoaded(($clinit_11() , BROWSER_LOADER), 1);
  !!$stats && $stats($createStatsEvent('runCallbacks1', 'begin', null, null));
  instance.runCallbacks();
  !!$stats && $stats($createStatsEvent('runCallbacks1', 'end', null, null));
}

function runCallbacks_0(){
}

function AsyncLoader1(){
}

_ = AsyncLoader1.prototype = new AsyncLoader1__Super;
_.runCallbacks = runCallbacks_0;
_.typeId$ = 0;
var instance;
onLoad();
