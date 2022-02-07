import { registerPlugin } from '@capacitor/core';

import type { FlashPlugin } from './definitions';

const Flash = registerPlugin<FlashPlugin>('Flash', {
  web: () => import('./web').then(m => new m.FlashWeb()),
});

export * from './definitions';
export { Flash };
