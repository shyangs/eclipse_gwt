function $fragmentHasLoaded(this$static, fragment){
  var logGroup;
  logGroup = fragment == this$static.numEntries?'leftoversDownload':'download' + fragment;
  $logEventProgress_0(logGroup, 'end', valueOf(fragment), null);
  if ($isInitial(this$static, fragment)) {
    $remove(this$static.remainingInitialFragments);
    $remove_4(this$static.initialFragmentErrorHandlers, valueOf(fragment));
    $startLoadingNextInitial(this$static);
  }
}

function $leftoversFragmentHasLoaded(this$static){
  $fragmentHasLoaded(this$static, this$static.numEntries);
}

function browserLoaderLeftoversFragmentHasLoaded(){
  $leftoversFragmentHasLoaded(BROWSER_LOADER);
}

browserLoaderLeftoversFragmentHasLoaded();
