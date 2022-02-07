import { WebPlugin } from '@capacitor/core';

import type { FlashPlugin } from './definitions';

export class FlashWeb extends WebPlugin implements FlashPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
