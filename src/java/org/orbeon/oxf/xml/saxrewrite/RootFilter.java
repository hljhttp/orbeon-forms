/**
 *  Copyright (C) 2004 Orbeon, Inc.
 *
 *  This program is free software; you can redistribute it and/or modify it under the terms of the
 *  GNU Lesser General Public License as published by the Free Software Foundation; either version
 *  2.1 of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU Lesser General Public License for more details.
 *
 *  The full text of the license is available at http://www.gnu.org/copyleft/lesser.html
 */
package org.orbeon.oxf.xml.saxrewrite;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * <!-- RootFilter -->
 * Ignores everything before start element.  On startElement switches to nextState.
 * So if this is used as the initial state then the result is that the prologue and epilogue
 * are ignored while the root element is passed to the next state.  nextState is initialized to
 * this, consequently nothing interesting will happen unless setNext is called.
 * @author d
 */
public class RootFilter extends State {
    
    protected State nextState = this;
    
    /**
     * <!-- RootFilter -->
     * Simple calls super(...)
     * @see State2#State(State2, ContentHandler, Response, boolean, boolean)
     * @author d
     * @param rwrtURI 
     */
    public RootFilter( final State stt, final ContentHandler cntntHnder ) {
        super( stt, cntntHnder );
    }
    public void setNextState( final State stt ) {
        nextState = stt;
    }
    /**
     * <!-- startElement -->
     * @return new RewriteState( ... )
     * @see RewriteState
     * @author d
     */
    public State startElement
    ( final String ns, final String lnam, final String qnam, final Attributes atts ) 
    throws SAXException {
        return nextState.startElement( ns, lnam, qnam, atts );
    }
    /**
     * <!-- characters -->
     * @return this.  Does nothing else.
     */
    public State characters( final char[] ch, final int strt, final int len ) 
    throws SAXException {
        return this;
    }
    /**
     * <!-- ignorableWhitespace -->
     * @return this.  Does nothing else.
     */
    public State ignorableWhitespace( final char[] ch, final int strt, final int len ) 
    throws SAXException {
        return this;
    }
    /**
     * <!-- processingInstruction -->
     * @return this.  Does nothing else.
     */
    public State processingInstruction( final String trgt, final String dat ) 
    throws SAXException {
        return this;
    }
}