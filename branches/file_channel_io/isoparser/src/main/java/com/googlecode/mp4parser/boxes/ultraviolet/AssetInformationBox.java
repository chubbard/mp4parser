/*
 * Copyright 2011 castLabs, Berlin
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.googlecode.mp4parser.boxes.ultraviolet;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.Utf8;
import com.coremedia.iso.boxes.AbstractFullBox;

import java.nio.ByteBuffer;

/**
 * AssetInformationBox as defined Common File Format Spec.
 */
public class AssetInformationBox extends AbstractFullBox {
    String apid;
    String profileVersion;

    public AssetInformationBox() {
        super("ainf");
    }

    @Override
    protected long getContentSize() {
        return Utf8.utf8StringLengthInBytes(apid) + 1 + 4;
    }


    @Override
    protected void getContent(ByteBuffer bb)  {
        bb.put(Utf8.convert(profileVersion), 0, 4);
        bb.put(Utf8.convert(apid));
        bb.put((byte) 0);
    }


    @Override
    public void _parseDetails(ByteBuffer content) {
        profileVersion = IsoTypeReader.readString(content, 4);
        apid = IsoTypeReader.readString(content);
        content = null;
    }

    public String getApid() {
        return apid;
    }

    public void setApid(String apid) {
        this.apid = apid;
    }

    public String getProfileVersion() {
        return profileVersion;
    }

    public void setProfileVersion(String profileVersion) {
        assert profileVersion != null && profileVersion.length() == 4;
        this.profileVersion = profileVersion;
    }
}
