import { Directive, ElementRef, HostListener } from "@angular/core";

@Directive({
    selector: '[onlyNumberComa]'
})
export class OnlyNumberComaDirective {

    regexStr = '^[0-9,]+$';

    constructor(private _el: ElementRef) {}

    @HostListener('keypress', ['$event'])
    onKeyPress(event: { key: string; }) {
        return new RegExp(this.regexStr).test(event.key);
    }

    @HostListener('paste', ['$event'])
    blockPaste(event: ClipboardEvent) {
        this.validateFields(event);
    }

    validateFields(event: ClipboardEvent) {
        event.preventDefault();
        const pasteData = event.clipboardData?.getData('text/plain').replace(/[^0-9,]/g, '');
        document.execCommand('insertHTML', false, pasteData);
    }
}