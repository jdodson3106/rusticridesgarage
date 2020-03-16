'use strict';

import storage from './config'

/*-----------------------------------------------
|   Demo mode
-----------------------------------------------*/
const setItem = (key, value) => localStorage.setItem(key, !value);

window.utils.$document.ready(() => {
  const { utils, location } = window;
  const { isDark, isRTL, isFluid } = storage;

  const Event = {
    CHANGE: 'change'
  }
  const ClassName = { 
    CONTAINER: 'container',
    CONTAINER_FLUID: 'container-fluid',
  }
  const Selector = {
    DATALAYOUT: '[data-layout]',
    BODY: 'body',
    DARK: '#dark',
    RTL: '#rtl',
    FLUID: '#fluid',
    THEMESTYLE: '#theme-style',
    STYLE: '.style'
  }
  const Attribute = { 
    DIR: 'dir',
    DISABLED: 'disabled'
  };

  const handleChange = (selector, key, value) => {
    utils.$document.on(Event.CHANGE, selector, () => {
      setItem(key, value);
      location.reload();
    });
  }

  const disabledAllStyles = () => {
    $(Selector.STYLE).each(function disabledStyles() {
      $(this).attr(Attribute.DISABLED, true);
    });
  };

  const $dataLayout = $(Selector.DATALAYOUT);

  // Fluid mode controler
  if(isFluid) {
    $dataLayout.addClass(ClassName.CONTAINER_FLUID).removeClass(ClassName.CONTAINER);
  } else {
    $dataLayout.addClass(ClassName.CONTAINER).removeClass(ClassName.CONTAINER_FLUID);
  }

  // Dark and RTL mode controler
  disabledAllStyles();
  utils.$html.attr(Attribute.DIR, isRTL ? 'rtl' : 'ltr');
  $(`${Selector.THEMESTYLE}${isDark ? '-dark' : ''}${isRTL ? '-rtl' : ''}`).removeAttr(Attribute.DISABLED);

  $(Selector.BODY).css('display', 'block');

  // Mode checkbox handler
  handleChange(Selector.DARK, 'isDark', isDark);
  handleChange(Selector.FLUID, 'isFluid', isFluid);
  handleChange(Selector.RTL, 'isRTL', isRTL);
});