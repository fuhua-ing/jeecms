package com.jeecms.cms;

import com.jeecms.core.entity.CmsUser;

public final class CalculateIdentityUtil
{

	public static int addIdentity(CmsUser cmsUser, IdentityEnum identityEnum)
	{
		if (null == cmsUser)
		{
			return 0;
		}
		Integer memeberType = cmsUser.getMemberType() == null ? 0 : cmsUser.getMemberType();
		Integer addIdentity = identityEnum.getValue();

		int changeIdentity = memeberType.intValue();

		switch (addIdentity)
		{
			case 1:
				if (memeberType.intValue() == 0)
				{
					changeIdentity = 1;
				}
				if (memeberType.intValue() == 1)
				{
					break;
				}
				if (memeberType.intValue() == 2)
				{
					changeIdentity = 4;
				}
				if (memeberType.intValue() == 3)
				{
					changeIdentity = 5;
				}
				if (memeberType.intValue() == 4)
				{
					break;
				}
				if (memeberType.intValue() == 5)
				{
					break;
				}
				if (memeberType.intValue() == 6)
				{
					changeIdentity = 7;
				}
				break;
			case 2:
				if (memeberType.intValue() == 0)
				{
					changeIdentity = 2;
				}
				if (memeberType.intValue() == 1)
				{
					changeIdentity = 4;
				}
				if (memeberType.intValue() == 2)
				{
					break;
				}
				if (memeberType.intValue() == 3)
				{
					changeIdentity = 6;
				}
				if (memeberType.intValue() == 4)
				{
					break;
				}
				if (memeberType.intValue() == 5)
				{
					changeIdentity = 7;
				}
				if (memeberType.intValue() == 6)
				{
					break;
				}
				break;
			case 3:
				if (memeberType.intValue() == 0)
				{
					changeIdentity = 3;
				}
				if (memeberType.intValue() == 1)
				{
					changeIdentity = 5;
				}
				if (memeberType.intValue() == 2)
				{
					changeIdentity = 6;
				}
				if (memeberType.intValue() == 3)
				{
					break;
				}
				if (memeberType.intValue() == 4)
				{
					changeIdentity = 7;
				}
				if (memeberType.intValue() == 5)
				{
					break;
				}
				if (memeberType.intValue() == 6)
				{
					break;
				}
				break;

			default:
				break;
		}
		if (memeberType.intValue() != changeIdentity)
		{
			return changeIdentity;
		}
		return memeberType;
	}

}
